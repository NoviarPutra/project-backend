package com.project.bootcamp_project.specification;

import com.project.bootcamp_project.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<User> getUserSpecification(
            String email,
            String roleName,
            String id,
            LocalDateTime createdAtStart,
            LocalDateTime createdAtEnd
    ) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (email != null && !email.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            }

            if (roleName != null && !roleName.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("role").get("name")), "%" + roleName.toLowerCase() + "%"));
            }

            if (id != null && !id.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("id"), "%" + id + "%"));
            }

            if (createdAtStart != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), createdAtStart));
            }
            if (createdAtEnd != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), createdAtEnd));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
