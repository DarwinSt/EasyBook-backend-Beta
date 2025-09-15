package org.example.easybookbackend.domain.dto.stay;

import java.time.OffsetDateTime;
import java.util.UUID;

public record StayDto(UUID id, String roomNumber, String guestEmail, String guestName,
                      OffsetDateTime checkInAt, OffsetDateTime checkOutAt, boolean active) {}