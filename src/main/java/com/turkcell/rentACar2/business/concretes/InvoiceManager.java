package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.*;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.InvoiceDto.InvoiceGetDto;
import com.turkcell.rentACar2.business.dtos.InvoiceDto.InvoiceListDto;
import com.turkcell.rentACar2.business.dtos.OrderedAdditionalServiceDto.OrderedAdditionalServiceListDto;
import com.turkcell.rentACar2.business.requests.InvoiceRequest.CreateInvoiceRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.InvoiceDao;
import com.turkcell.rentACar2.entities.concretes.Car;
import com.turkcell.rentACar2.entities.concretes.Invoice;
import com.turkcell.rentACar2.entities.concretes.RentalCar;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private InvoiceDao invoiceDao;
    private ModelMapperService modelMapperService;
    private OrderedAdditionalServiceService orderedAdditionalServiceService;
    @Lazy private RentalCarService rentalCarService;
    private AdditionalServiceService additionalServiceService;
    private CarService carService;

    @Override
    public DataResult<List<InvoiceListDto>> getAll() {
        List<Invoice> invoices = this.invoiceDao.findAll();

        List<InvoiceListDto> invoiceListDto = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(invoiceListDto, BusinessMessages.DATA_LISTED);
    }

    @Override
    public DataResult<InvoiceGetDto> getById(int id) {
        checkIfIdExists(id);

        Invoice invoice = this.invoiceDao.getById(id);
        InvoiceGetDto invoiceGetDto = this.modelMapperService.forDto().map(invoice, InvoiceGetDto.class);

        return new SuccessDataResult<>(invoiceGetDto,BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public DataResult<Invoice> add(CreateInvoiceRequest createInvoiceRequest) {
        checkIfRentalCarExists(createInvoiceRequest.getRentalCarId());

        Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);

        this.populateInvoiceFields(invoice, createInvoiceRequest);
        this.invoiceDao.save(invoice);

        return new SuccessDataResult<>(invoice, BusinessMessages.DATA_ADDED);
    }

    @Override
    public DataResult<Invoice> addForDelay(CreateInvoiceRequest createInvoiceRequest) {
        checkIfRentalCarExists(createInvoiceRequest.getRentalCarId());

        Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);

        this.populateInvoiceFieldsForDelay(invoice, createInvoiceRequest);
        this.invoiceDao.save(invoice);

        return new SuccessDataResult<>(invoice, BusinessMessages.DATA_ADDED);
    }

    @Override
    public Result delete(int id) {
        this.checkIfIdExists(id);

        this.invoiceDao.deleteById(id);

        return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    @Override
    public DataResult<List<InvoiceListDto>> getByInvoiceDate(LocalDate startDate, LocalDate endDate) {
        checkIfEndDateIsAfterOrEqualStartDate(startDate, endDate);

        List<Invoice> invoices = this.invoiceDao.findByInvoiceDateBetween(startDate, endDate);

        List<InvoiceListDto> response = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.INVOICES_LISTED_BY_INVOICE_DATE + startDate + " - " + endDate);
    }

    private double payment(int rentalCarId) {
        RentalCar rentalCar = this.rentalCarService.findById(rentalCarId);
        Car car = this.carService.findById(rentalCar.getCar().getId());

        long dayDiff = this.rentalCarService.calculateRentalDayDiff(rentalCar);

        if (dayDiff == 0) {
            return car.getDailyPrice();
        } else {
            return (dayDiff + 1) * (car.getDailyPrice());
        }
    }

    private double additionalServicePayment(int rentalCarId) {

        double additionalServicePayment = 0;
        List<OrderedAdditionalServiceListDto> orderedAdditionalServices = this.orderedAdditionalServiceService.getByRentalCarId(rentalCarId).getData();

        if (orderedAdditionalServices == null) {
            return additionalServicePayment;
        }
        for (OrderedAdditionalServiceListDto orderedAdditionalService : orderedAdditionalServices) {
            Double additionalServiceDailyPrice =this.additionalServiceService.findById(orderedAdditionalService.getAdditionalServiceId()).getDailyPrice();
            int quantitiy = orderedAdditionalService.getQuantity();
            additionalServicePayment += additionalServiceDailyPrice * quantitiy;
        }
        return additionalServicePayment;
    }

    private double locationServicePayment(int rentalCarId) {

        RentalCar rentalCar = this.rentalCarService.findById(rentalCarId);

        return rentalCar.getFromCity().getId() != rentalCar.getToCity().getId() ? 750 : 0;
    }

    private double delayedDayPayment(int rentalCarId) {
        RentalCar rentalCar = this.rentalCarService.findById(rentalCarId);

        long delayedDayDiff = this.rentalCarService.calculateDelayedRentalDayDiff(rentalCarId);

        return delayedDayDiff * (rentalCar.getCar().getDailyPrice());
    }

    private void checkIfIdExists(int id) {
        if (!this.invoiceDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.INVOICE_DOES_NOT_EXISTS + id);
        }
    }

    private void populateInvoiceFields(Invoice invoice, CreateInvoiceRequest createInvoiceRequest) {
        double additionalServicePayment = additionalServicePayment(createInvoiceRequest.getRentalCarId());
        double locationServicePayment = locationServicePayment(createInvoiceRequest.getRentalCarId());
        double payment = payment(createInvoiceRequest.getRentalCarId());

        RentalCar rentalCar = this.rentalCarService.findById(createInvoiceRequest.getRentalCarId());

        int dayDiff = (int) DAYS.between(rentalCar.getRentDate(), rentalCar.getReturnDate());

        invoice.setId(0);
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setInvoiceNumber(100000000 + new Random().nextInt(900000000));
        invoice.setTotalRentalDays(dayDiff);
        invoice.setTotalPayment(additionalServicePayment + locationServicePayment + payment);
    }

    private void populateInvoiceFieldsForDelay(Invoice invoice, CreateInvoiceRequest createInvoiceRequest) {
        int totalRentalDays = (int)this.rentalCarService.calculateDelayedRentalDayDiff(createInvoiceRequest.getRentalCarId());

        invoice.setId(0);
        invoice.setTotalRentalDays(totalRentalDays);
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setInvoiceNumber(100000000 + new Random().nextInt(900000000));
        invoice.setTotalPayment(delayedDayPayment(createInvoiceRequest.getRentalCarId()));
    }

    @Override
    public DataResult<List<InvoiceListDto>> findAllByCustomerId(int customerId) {
        List<Invoice> invoices = this.invoiceDao.findByRentalCar_CustomerId(customerId);

        List<InvoiceListDto> response = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.INVOICES_LISTED_BY_CUSTOMER_ID + customerId);
    }

    private void checkIfEndDateIsAfterOrEqualStartDate(LocalDate startDate, LocalDate endDate) {
        if (!endDate.isAfter(startDate) && !endDate.equals(startDate)) {
            throw new BusinessException(BusinessMessages.INVALID_INVOICE_DATES);
        }
    }

    private void checkIfRentalCarExists(int rentalCarId) {
        this.rentalCarService.checkIfIdExists(rentalCarId);
    }

}
