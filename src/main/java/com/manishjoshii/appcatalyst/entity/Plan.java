package com.manishjoshii.appcatalyst.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(unique = true)
    String stripePriceId;

    Integer maxProjects;
    Integer maxTokensPerDay;
    Integer maxPreviews; // max number of previews allowed per plan
    Boolean unlimitedAi; // unlimited access to LLM, ignore maxTokensPerDay if true

    Boolean active;
}
/*
 * INSERT INTO plan (id, name, stripe_price_id, max_projects,
 * max_tokens_per_day, max_previews, unlimited_ai, active)
 * VALUES (1, 'Pro Plan', '{STRIPE_PRICE_ID}', 3, 10000, 1, TRUE, TRUE);
 * 
 * INSERT INTO plan (id, name, stripe_price_id, max_projects,
 * max_tokens_per_day, max_previews, unlimited_ai, active)
 * VALUES (2, 'Business Plan', '{STRIPE_PRICE_ID}', 10, 50000, 3, TRUE, TRUE);
 */