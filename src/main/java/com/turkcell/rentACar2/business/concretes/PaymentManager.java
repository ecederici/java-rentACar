package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.api.models.CardDetails;
import com.turkcell.rentACar2.api.models.PaymentModel;
import com.turkcell.rentACar2.api.models.ReturnedRentalCarModel;
import com.turkcell.rentACar2.business.abstracts.*;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.PaymentDto.PaymentGetDto;
import com.turkcell.rentACar2.business.dtos.PaymentDto.PaymentListDto;
import com.turkcell.rentACar2.business.requests.InvoiceRequest.CreateInvoiceRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentACar2.entities.concretes.Invoice;
import com.turkcell.rentACar2.entities.concretes.Payment;
import com.turkcell.rentACar2.entities.concretes.RentalCar;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentManager implements PaymentService {
    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    @Lazy private RentalCarService rentalCarService;
    @Lazy private InvoiceService invoiceService;
    private PosService posService;
    private UserCardInformationService userCardInformationService;

    public PaymentManager(PaymentDao paymentDao,
                          ModelMapperService modelMapperService,
                          RentalCarService rentalCarService,
                          InvoiceService invoiceService,
                          PosService posService,
                          UserCardInformationService userCardInformationService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.rentalCarService = rentalCarService;
        this.invoiceService = invoiceService;
        this.posService = posService;
        this.userCardInformationService = userCardInformationService;
    }

    @Override
    public DataResult<List<PaymentListDto>> getAll() {
        List<Payment> payments = this.paymentDao.findAll();

        List<PaymentListDto> response = payments.stream().map(payment -> this.modelMapperService.forDto().map(payment, PaymentListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<PaymentGetDto> getById(int id) {
        checkIfIdExists(id);

        Payment payment = this.paymentDao.getById(id);

        PaymentGetDto response = this.modelMapperService.forDto().map(payment, PaymentGetDto.class);

        return new SuccessDataResult<>(response, BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result add(PaymentModel paymentModel) {
        createPayment(paymentModel);

        return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public Result delete(int id) {
        checkIfIdExists(id);

        this.paymentDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    private void checkIfIdExists(int id) {
        if (this.paymentDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.PAYMENT_DOES_NOT_EXISTS + id);
        }
    }

    private void checkIfPaymentSuccess(CardDetails cardDetails, Invoice invoice) {
        if (!posService.makePayment(cardDetails, invoice.getTotalPayment())) {
            throw new BusinessException(BusinessMessages.PAYMENT_FAILED);
        }
    }

    @Transactional
    private void createPayment(PaymentModel paymentModel) {
        RentalCar rentalCar = this.rentalCarService.add(paymentModel.getRentalCarModel()).getData();

        CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();

        createInvoiceRequest.setRentalCarId(rentalCar.getId());

        Invoice invoice = this.invoiceService.add(createInvoiceRequest).getData();

        checkIfPaymentSuccess(paymentModel.getCreateUserCardInformationRequest().toCardDetails(), invoice);

        Payment payment = this.modelMapperService.forRequest().map(paymentModel, Payment.class);

        populatePaymentFields(payment, rentalCar, invoice);

        saveUserCardDetails(paymentModel, rentalCar);

        this.paymentDao.save(payment);
    }

    @Override
    @Transactional
    public void createPaymentForDelay(int id, ReturnedRentalCarModel returnedRentalCarModel) {

        CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();

        createInvoiceRequest.setRentalCarId(id);

        Invoice invoice = this.invoiceService.addForDelay(createInvoiceRequest).getData();

        checkIfPaymentSuccess(returnedRentalCarModel.getCreateUserCardInformationRequest().toCardDetails(), invoice);

        Payment payment = this.modelMapperService.forRequest().map(returnedRentalCarModel, Payment.class);
        RentalCar rentalCar = this.rentalCarService.findById(id);

        populatePaymentFields(payment, rentalCar, invoice);

        this.paymentDao.save(payment);

    }

    private void populatePaymentFields(Payment payment, RentalCar rentalCar, Invoice invoice) {
        payment.setRentalCar(rentalCar);
        payment.setPaymentAmount(invoice.getTotalPayment());
        payment.setInvoice(invoice);
    }

    private void saveUserCardDetails(PaymentModel paymentModel, RentalCar rentalCar) {
        if (paymentModel.isSaveCard()) {
            this.userCardInformationService.add(paymentModel.getCreateUserCardInformationRequest(), rentalCar.getCustomer().getId());
        }
    }
}
