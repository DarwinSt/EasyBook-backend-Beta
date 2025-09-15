package org.example.easybookbackend.domain.dto.inventory;

public record CreateInventoryItemRequest(String name, Integer quantity, String unit) {}