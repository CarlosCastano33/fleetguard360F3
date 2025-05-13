package com.FleetGuard360F3.domain.entities;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "passengers")

public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
}
