package com.hibernate.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ItemDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    int id;
    @NonNull
    @Column(nullable = false)
    int width;
    @NonNull
    @Column(nullable = false)
    int height;
    @NonNull
    @Column(nullable = false)
    int weight;


    @OneToOne(mappedBy = "itemDescription", cascade = CascadeType.ALL, orphanRemoval = true)
    private Item item;

}
