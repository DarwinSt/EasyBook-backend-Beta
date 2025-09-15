package org.example.easybookbackend.domain.dto.stay;

public record CheckInRequest(String roomNumber, String guestEmail, String guestFullName) {}