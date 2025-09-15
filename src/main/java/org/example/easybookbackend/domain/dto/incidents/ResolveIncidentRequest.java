package org.example.easybookbackend.domain.dto.incidents;

import java.util.List;

public record ResolveIncidentRequest(List<ItemQty> consumption) {
    public static record ItemQty(String name, int quantity) {}
}