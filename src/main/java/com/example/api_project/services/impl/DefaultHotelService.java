package com.example.api_project.services.impl;

import com.example.api_project.converters.HotelConverter;
import com.example.api_project.dto.createRequestDto.HotelCreateData;
import com.example.api_project.dto.HotelDetailedData;
import com.example.api_project.dto.HotelShortData;
import com.example.api_project.dto.statisitcs.AmenityStatistic;
import com.example.api_project.dto.statisitcs.BrandStatistic;
import com.example.api_project.dto.statisitcs.CityStatistic;
import com.example.api_project.dto.statisitcs.CountryStatistic;
import com.example.api_project.entities.Hotel;
import com.example.api_project.exceptions.HotelAlreadyExistsException;
import com.example.api_project.exceptions.HotelNotFoundException;
import com.example.api_project.repositories.HotelRepository;
import com.example.api_project.services.HotelService;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.api_project.specifications.HotelSpecifications.*;

@Service
public class DefaultHotelService implements HotelService {
    @Resource
    private HotelRepository hotelRepository;
    @Resource
    private HotelConverter hotelConverter;

    @Override
    public List<HotelShortData> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelConverter::convertToShortData)
                .toList();
    }

    @Override
    public HotelDetailedData getHotelById(final Long id) {
        return hotelRepository.findById(id)
                .map(hotelConverter::convertToDetailedData)
                .orElse(null);
    }

    public List<HotelShortData> searchHotels(final String name, final String brand, final String city,
                                             final String country, final String amenities) {
        final Specification<Hotel> spec = Specification.allOf(
                hasName(name),
                hasBrand(brand),
                hasCity(city),
                hasCountry(country),
                hasAmenity(amenities)
        );

        return hotelRepository.findAll(spec)
                .stream()
                .map(hotelConverter::convertToShortData)
                .toList();
    }

    @Override
    public HotelShortData createHotel(final HotelCreateData hotelCreateData) {
        // just simple validation to avoid duplicates by name
        if (hotelRepository.existsByName(hotelCreateData.getName())) {
            throw new HotelAlreadyExistsException("Hotel with name " + hotelCreateData.getName() + " already exists.");
        }
        final Hotel hotel = hotelConverter.convertToEntity(hotelCreateData);
        final Hotel savedHotel = hotelRepository.save(hotel);
        return hotelConverter.convertToShortData(savedHotel);
    }

    @Override
    @Transactional
    public void addAmenities(final Long hotelId, final List<String> amenities) {
        final Hotel hotel = hotelRepository.findById(hotelId) //optional
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + hotelId));

        List<String> currentAmenities = hotel.getAmenities();
        if (currentAmenities == null) {
            currentAmenities = new ArrayList<>();
            hotel.setAmenities(currentAmenities);
        }

        for (final String amenity : amenities) {
            if (!currentAmenities.contains(amenity)) {
                currentAmenities.add(amenity);
            }
        }
        hotelRepository.save(hotel);
    }

    @Override
    public Map<String, Long> getHistogram(String param) {
        return switch (param.toLowerCase()) {
            case "brand" -> convertBrandToMap(hotelRepository.countByBrand());
            case "city" -> convertCityToMap(hotelRepository.countByCity());
            case "country" -> convertCountryToMap(hotelRepository.countByCountry());
            case "amenities" -> convertAmenityToMap(hotelRepository.countByAmenities());
            default -> throw new IllegalArgumentException("Invalid parameter: " + param);
        };
    }

    private Map<String, Long> convertBrandToMap(List<BrandStatistic> stats) {
        return stats.stream()
                .collect(Collectors.toMap(
                        BrandStatistic::brand,
                        BrandStatistic::count
                ));
    }

    private Map<String, Long> convertCityToMap(List<CityStatistic> stats) {
        return stats.stream()
                .collect(Collectors.toMap(
                        CityStatistic::city,
                        CityStatistic::count
                ));
    }

    private Map<String, Long> convertCountryToMap(List<CountryStatistic> stats) {
        return stats.stream()
                .collect(Collectors.toMap(
                        CountryStatistic::country,
                        CountryStatistic::count
                ));
    }

    private Map<String, Long> convertAmenityToMap(List<AmenityStatistic> stats) {
        return stats.stream()
                .collect(Collectors.toMap(
                        AmenityStatistic::amenity,
                        AmenityStatistic::count
                ));
    }
}
