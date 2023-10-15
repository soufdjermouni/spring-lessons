package com.spring.lessons.springlessons.domain.batch;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "request")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String code;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name ="customer_id")
    private Customer customer ;
}
