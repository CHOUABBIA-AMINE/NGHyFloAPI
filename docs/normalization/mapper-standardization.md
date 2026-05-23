# Mapper Standardization

## Mapper Placement
- API mappers: `api.rest.mapper`
- Persistence mappers: `infrastructure.persistence.*`
- Cross-context translators: dedicated anti-corruption mapper packages.

## Mapper Rules
1. One mapper responsibility per boundary (API ↔ app DTO, domain ↔ persistence).
2. No business rules inside mappers.
3. Nullability and defaulting policy centralized and deterministic.
4. Mapping exceptions routed through normalized exception layer.

## Canonicalization
- Remove duplicate mapping behavior by centralizing per boundary type.
- Enforce test coverage for each mapper transformation path.
