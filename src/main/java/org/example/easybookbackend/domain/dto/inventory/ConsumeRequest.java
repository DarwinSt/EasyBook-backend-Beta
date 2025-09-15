package org.example.easybookbackend.domain.dto.inventory;

import java.util.List;

public record ConsumeRequest(List<ItemQty> items) {
    public static record ItemQty(String name, int quantity) {}
}