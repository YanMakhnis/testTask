package com.example.api_project.services.impl;

import com.example.api_project.converters.HotelConverter;
import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import com.example.api_project.dto.createRequestDto.HotelCreateData;
import com.example.api_project.entities.Hotel;
import com.example.api_project.exceptions.HotelAlreadyExistsException;
import com.example.api_project.repositories.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultHotelServiceTest {
    @InjectMocks
    private DefaultHotelService hotelService;
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private HotelConverter hotelConverter;

    @Test
    void getAllHotels_ShouldReturnListOfHotels_WhenHotelsExist() {
        // given
        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel();
        List<Hotel> hotels = List.of(hotel1, hotel2);

        HotelShortData dto1 = new HotelShortData(1L, "Hotel 1", null, null, null);
        HotelShortData dto2 = new HotelShortData(2L, "Hotel 2", null, null, null);

        Mockito.when(hotelRepository.findAll()).thenReturn(hotels);
        Mockito.when(hotelConverter.convertToShortData(hotel1)).thenReturn(dto1);
        Mockito.when(hotelConverter.convertToShortData(hotel2)).thenReturn(dto2);

        // when
        List<HotelShortData> result = hotelService.getAllHotels();

        // then
        assertThat(result).hasSize(2);
        verify(hotelRepository).findAll();
    }

    @Test
    void getAllHotels_ShouldReturnEmptyList_WhenNoHotelsExist() {
        // given
        when(hotelRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        List<HotelShortData> result = hotelService.getAllHotels();

        // then
        assertThat(result).isEmpty();
        verify(hotelRepository).findAll();
    }

    @Test
    void getHotelById_ShouldReturnHotel_WhenExists() {
        // given
        Long hotelId = 1L;
        Hotel hotel = new Hotel();
        HotelDetailedData detailedDto = new HotelDetailedData();
        detailedDto.setId(1L);

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
        when(hotelConverter.convertToDetailedData(hotel)).thenReturn(detailedDto);

        // when
        HotelDetailedData result = hotelService.getHotelById(hotelId);

        // then
        assertThat(result).isEqualTo(detailedDto);
        assertThat(result.getId()).isEqualTo(1L);
        verify(hotelRepository).findById(hotelId);
    }

    @Test
    void getHotelById_ShouldReturnNull_WhenNotExists() {
        // given
        Long hotelId = 999L;
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        // when
        HotelDetailedData result = hotelService.getHotelById(hotelId);

        // then
        assertThat(result).isNull();
        verify(hotelRepository).findById(hotelId);
    }


    @Test
    void createHotel_ShouldSaveAndReturnHotel_WhenNameDoesNotExist() {
        // given
        HotelCreateData createData = new HotelCreateData();
        createData.setName("New Hotel");
        createData.setBrand("Test Brand");

        Hotel hotel = new Hotel();
        Hotel savedHotel = new Hotel();
        savedHotel.setId(1L);
        HotelShortData expectedDto = new HotelShortData(1L, "New Hotel", null, null, null);

        when(hotelRepository.existsByName("New Hotel")).thenReturn(false);
        when(hotelConverter.convertToEntity(createData)).thenReturn(hotel);
        when(hotelRepository.save(hotel)).thenReturn(savedHotel);
        when(hotelConverter.convertToShortData(savedHotel)).thenReturn(expectedDto);

        // when
        HotelShortData result = hotelService.createHotel(createData);

        // then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("New Hotel");
        verify(hotelRepository).existsByName("New Hotel");
        verify(hotelRepository).save(hotel);
    }

    @Test
    void createHotel_ShouldThrowException_WhenNameAlreadyExists() {
        // given
        HotelCreateData createData = new HotelCreateData();
        createData.setName("Existing Hotel");

        when(hotelRepository.existsByName("Existing Hotel")).thenReturn(true);

        // when/then
        assertThatThrownBy(() -> hotelService.createHotel(createData))
                .isInstanceOf(HotelAlreadyExistsException.class)
                .hasMessageContaining("Hotel with name Existing Hotel already exists");

        verify(hotelRepository, never()).save(any());
    }

    @Test
    void addAmenities_ShouldAddNewAmenities_WhenHotelExists() {
        // given
        Long hotelId = 1L;
        List<String> newAmenities = List.of("WiFi", "Parking");
        Hotel hotel = new Hotel();
        hotel.setAmenities(new ArrayList<>(List.of("WiFi")));

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        // when
        hotelService.addAmenities(hotelId, newAmenities);

        // then
        assertThat(hotel.getAmenities()).containsExactlyInAnyOrder("WiFi", "Parking");
        verify(hotelRepository).save(hotel);
    }

    @Test
    void addAmenities_ShouldInitializeAmenities_WhenNull() {
        // given
        Long hotelId = 1L;
        List<String> newAmenities = List.of("WiFi", "Parking");
        Hotel hotel = new Hotel();
        hotel.setAmenities(null);

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        // when
        hotelService.addAmenities(hotelId, newAmenities);

        // then
        assertThat(hotel.getAmenities()).containsExactlyInAnyOrder("WiFi", "Parking");
        verify(hotelRepository).save(hotel);
    }

    @Test
    void searchHotels_ShouldReturnFilteredHotels_WhenParametersProvided() {
        // given
        String name = "Hilton";
        String city = "Minsk";

        Hotel hotel = new Hotel();
        HotelShortData dto = new HotelShortData(1L, "Hilton Minsk", null, null, null);

        when(hotelRepository.findAll(any(Specification.class))).thenReturn(List.of(hotel));
        when(hotelConverter.convertToShortData(hotel)).thenReturn(dto);

        // when
        List<HotelShortData> result = hotelService.searchHotels(name, null, city, null, null);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Hilton Minsk");
        verify(hotelRepository).findAll(any(Specification.class));
    }

    @Test
    void searchHotels_ShouldReturnEmptyList_WhenNoMatches() {
        // given
        when(hotelRepository.findAll(any(Specification.class))).thenReturn(Collections.emptyList());

        // when
        List<HotelShortData> result = hotelService.searchHotels("Nonexistent", null, null, null, null);

        // then
        assertThat(result).isEmpty();
    }
}