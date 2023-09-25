package com.tenpo.challenge.challenge.application.usecase;

import com.tenpo.challenge.challenge.application.exception.NotFoundException;
import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import com.tenpo.challenge.challenge.domain.PercentageDomain;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.BeforeEach;

import com.tenpo.challenge.challenge.application.port.out.ConsultJdbc;
import com.tenpo.challenge.challenge.application.port.out.PercentageRest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class GetCalculationUseCaseTest {
    private PercentageRest percentageRest;
    private ConsultJdbc consultJdbc;
    private GetCalculationUseCase getCalculationUseCase;
    private MockFactoryDomain mockFactoryDomain;

    private final static Double FIRST_NUMBER = 5.1;
    private final static Double SECOND_NUMBER = 3.1;

    @BeforeEach
    void setUp() {
        consultJdbc = Mockito.mock(ConsultJdbc.class);
        percentageRest = Mockito.mock(PercentageRest.class);
        getCalculationUseCase = new GetCalculationUseCase(percentageRest, consultJdbc);
        mockFactoryDomain = new MockFactoryDomain();
    }

    @Test
    void testExecuteWithServiceSuccess() {
        PercentageDomain percentageDomain = mockFactoryDomain.getPercentageDomain();
        CalculationResultDomain calculationResultDomain = mockFactoryDomain.getCalculationResultDomain();

        when(percentageRest.executeGetPercentage()).thenReturn(percentageDomain);
        when(consultJdbc.saveConsult(eq(FIRST_NUMBER), eq(SECOND_NUMBER), any(PercentageDomain.class))).thenReturn(calculationResultDomain);

        CalculationResultDomain result = getCalculationUseCase.execute(FIRST_NUMBER, SECOND_NUMBER);

        assertEquals(calculationResultDomain, result);
        verify(percentageRest, times(1)).executeGetPercentage();
        verify(consultJdbc, times(1)).saveConsult(eq(FIRST_NUMBER), eq(SECOND_NUMBER), any(PercentageDomain.class));
    }

    @Test
    void testExecuteWithoutServiceSuccess() {
        CalculationResultDomain calculationResultDomain = mockFactoryDomain.getCalculationResultDomain();

        when(percentageRest.executeGetPercentage()).thenReturn(PercentageDomain.builder().build());
        when(consultJdbc.getLastConsult()).thenReturn(calculationResultDomain);
        when(consultJdbc.saveConsult(eq(FIRST_NUMBER), eq(SECOND_NUMBER), any(PercentageDomain.class))).thenReturn(calculationResultDomain);

        CalculationResultDomain result = getCalculationUseCase.execute(FIRST_NUMBER, SECOND_NUMBER);

        assertEquals(calculationResultDomain, result);
        verify(percentageRest, times(1)).executeGetPercentage();
        verify(consultJdbc, times(1)).getLastConsult();
        verify(consultJdbc, times(1)).saveConsult(eq(FIRST_NUMBER), eq(SECOND_NUMBER), any(PercentageDomain.class));
    }

    @Test
    void testRecover() {
        NotFoundException e = new NotFoundException("The percentage is not found");

        NotFoundException thrownException = assertThrows(NotFoundException.class,
                () -> getCalculationUseCase.recover(e));

        assertEquals(e.getMessage(), thrownException.getMessage());
    }
}