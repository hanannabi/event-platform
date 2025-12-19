package com.example.event_management.theory;

public class Theory {
}
///🟢 HOW TO EXPLAIN IN INTERVIEW (Say this 👇)
//
//“Initially, my Event Management system was a monolith.
//I then extracted User and Event into independent microservices.
//Reservation Service communicates with them using Feign Clients.
//To handle failures and avoid cascading issues, I integrated Resilience4j with Circuit Breaker and fallback logic.”

///🟢 STEP 6: Final Architecture (Interview Gold ⭐)
//                ┌────────────┐
//                │ User Service│
//                │  (8081)     │
//                └─────▲──────┘
//                      │ Feign
//┌──────────────┐      │
//│ Reservation  │──────┘
//│ Service      │
//│ (8083)       │──────► Event Service (8082)
//└──────────────┘
//        ▲
//        │
//   Resilience4j
// (Circuit Breaker)
