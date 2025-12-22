package com.example.event_management.client;
/// 🎯 Scenario We Are Solving
//👉 Event / Reservation Service wants to check if user exists
//👉 User Service owns user data
//👉 They are separate applications

///🟢 STEP 1: User Service (Provider – NO Feign here)
//User Service only EXPOSES APIs

///🟢 STEP 2: Decide Who Needs User Validation
//Ask yourself:
//“Which service needs user info?”
//✔ Reservation Service → YES
//✔ Event Service → maybe (organizer validation)
//We’ll assume Reservation Service for now

import com.example.event_management.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

///🟢 STEP 3: Create Feign Client (Reservation Service)
/// 🟢 STEP 4: Add Feign Dependency (Reservation Service)
/// 🧠 Interview-Ready Explanation
/// Spring Cloud dependencies are not standalone. They require a compatible Spring Boot version and a Spring Cloud BOM.
/// Using an invalid Boot version or missing BOM causes Maven to resolve Feign with an “unknown” version, leading to artifact not found errors.
/// 🟢 STEP 5: Enable Feign (Reservation Service)
/// 🟢 STEP 6: Create UserClient (Feign Interface)
/// 📁 event-management-service/client/UserClient.java

//How FeignClient and RestTemplate relate to Resilience4j
//Key idea:
//
//FeignClient / RestTemplate make calls.
//Resilience4j protects those calls.

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);
}


///Perfect — you’ve reached the exact next milestone in a real microservices journey 👌
//You now have:
//
//✅ Two independent services
//✅ HTTP communication via Feign
//➡️ Next logical step: fault tolerance with Resilience4j
//
//I’ll explain this in 3 layers:
//
///Why Resilience4j is needed (real problems)
//
///What Resilience4j gives you (concepts)
//
///How to use it in YOUR project (step-by-step with examples)


///1️⃣ Why do you NEED Resilience4j now?
//Right now your flow is:
///Client → Event Service → (Feign) → User Service
//What if:
//User Service is down
//User Service is slow
//Network timeout happens
//User Service throws 500 error
///👉 Without Resilience4j:
//Event Service hangs
//Threads get blocked
//Requests pile up
//Your entire system becomes slow or crashes
//This is called a cascading failure.
///🎯 Goal of Resilience4j
/// “Failure of one service should NOT bring down other services.”

///2️⃣ What Resilience4j provides (core patterns)
//Think of Resilience4j as safety mechanisms around remote calls.

//| Pattern         | What it does                    | Real-life analogy    |
//| --------------- | ------------------------------- | -------------------- |
//| Circuit Breaker | Stops calling a failing service | Power trip switch    |
//| Retry           | Retries failed calls            | Redial if call drops |
//| TimeLimiter     | Fails slow calls                | Don’t wait forever   |
//| Bulkhead        | Limits concurrent calls         | Separate queues      |
//| RateLimiter     | Limits request rate             | Entry tokens         |

//👉 In your case, start with:
//Circuit Breaker
//Retry
//(later) TimeLimiter

///3️⃣ How Resilience4j fits into YOUR project
//Where exactly?
//👉 On the Feign call from event-service → user-service
//userClient.getUserById(userId);
//This is the remote dependency → protect it.

///4️⃣ Step-by-step: Add Resilience4j

///**************************************************************
//✅ STEP 1: Add dependencies (event-service)
//✅ STEP 2: Configure Resilience4j in application.yml
//👉 This config is for User Service calls
//✅ STEP 3: Apply it to your Feign call
//Option A (Most common): Apply in Service layer
//@CircuitBreaker(name = "userService", fallbackMethod = "userFallback")
//@Retry(name = "userService")
//public void validateUser(Long userId) {
//    userClient.getUserById(userId);
//}
//Fallback method
//public void userFallback(Long userId, Throwable ex) {
//    throw new UserServiceUnavailableException(
//        "User service is temporarily unavailable. Please try later."
//    );
//}

//✅ STEP 4: Use it inside your reservation flow
/// 🔥 Protected remote call
//    validateUser(request.getUserId());
