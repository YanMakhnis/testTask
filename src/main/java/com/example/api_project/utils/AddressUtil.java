package com.example.api_project.utils;

import com.example.api_project.entities.Address;

/**
 * Utility class for address formatting.
 */
public class AddressUtil {
    private static final String SPACE = " ";
    private static final String COMMA_SPACE = ", ";

    private AddressUtil() {
    }

    /**
     * Formats Address entity to readable string.
     *
     * @param address Address object to format
     * @return formatted address string, or null if address is null
     */
    public static String resolveAddress(Address address) {
        if (address == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (address.getHouseNumber() != null) {
            sb.append(address.getHouseNumber()).append(SPACE);
        }
        if (address.getStreet() != null) {
            sb.append(address.getStreet()).append(COMMA_SPACE);
        }
        if (address.getCity() != null) {
            sb.append(address.getCity()).append(COMMA_SPACE);
        }
        if (address.getPostCode() != null) {
            sb.append(address.getPostCode()).append(COMMA_SPACE);
        }
        if (address.getCountry() != null) {
            sb.append(address.getCountry());
        }

        return sb.toString();
    }
}
