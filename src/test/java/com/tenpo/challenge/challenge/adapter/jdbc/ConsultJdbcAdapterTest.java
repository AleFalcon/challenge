package com.tenpo.challenge.challenge.adapter.jdbc;

import com.tenpo.challenge.challenge.application.exception.NotFoundException;
import com.tenpo.challenge.challenge.application.port.out.ConsultPercentageRepository;
import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import com.tenpo.challenge.challenge.domain.ConsultEntity;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConsultJdbcAdapterTest {
    private ConsultPercentageRepository consultPercentageRepository;
    private ConsultJdbcAdapter consultJdbcAdapter;
    private MockFactoryDomain mockFactoryDomain;
    private final static Double FIRST_NUMBER = 5.1;
    private final static Double SECOND_NUMBER = 3.1;
    @BeforeEach
    void setUp() {
        consultPercentageRepository = Mockito.mock(ConsultPercentageRepository.class);
        consultJdbcAdapter = new ConsultJdbcAdapter(consultPercentageRepository);
        mockFactoryDomain = new MockFactoryDomain();
    }

    @Test
    void testSaveConsultSuccess() {
        ConsultEntity consultEntity = mockFactoryDomain.getConsultEntity();
        when(consultPercentageRepository.save(any(ConsultEntity.class))).thenReturn(consultEntity);

        CalculationResultDomain calculationResultDomain = consultJdbcAdapter.saveConsult(FIRST_NUMBER, SECOND_NUMBER, mockFactoryDomain.getPercentageDomain());

        double sum = ( consultEntity.getFirstNumber() + consultEntity.getSecondNumber());
        double result = sum * consultEntity.getPercentage() / 100 + sum;

        assertEquals(calculationResultDomain.getResult(), result);
        assertEquals(calculationResultDomain.getPercentage(), consultEntity.getPercentage());

        verify(consultPercentageRepository, times(1)).save(any(ConsultEntity.class));
    }

    @Test
    void testGetLastConsultSuccess() {
        ConsultEntity consultEntity = mockFactoryDomain.getConsultEntity();
        when(consultPercentageRepository.findFirstByOrderByCreatedAtDesc()).thenReturn(Optional.ofNullable(consultEntity));

        CalculationResultDomain calculationResultDomain = consultJdbcAdapter.getLastConsult();

        assert consultEntity != null;
        assertEquals(calculationResultDomain.getPercentage(), consultEntity.getPercentage());

        verify(consultPercentageRepository, times(1)).findFirstByOrderByCreatedAtDesc();
    }

    @Test
    void testGetLastConsultFailed() {

        when(consultPercentageRepository.findFirstByOrderByCreatedAtDesc()).thenReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> consultJdbcAdapter.getLastConsult());

        assertThat(thrown).isInstanceOf(NotFoundException.class);
        verify(consultPercentageRepository, times(1)).findFirstByOrderByCreatedAtDesc();
    }
}
