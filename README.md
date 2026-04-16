# Fraud Detection System

## Overview
The Fraud Detection System is designed to evaluate banking transactions in real-time to detect potentially fraudulent activity. It leverages a fast rules-based engine for immediate detection and integrates with a Machine Learning (ML) prediction API for more nuanced, probabilistic evaluation of transactions.

## Core Principles
1. **Real-Time Evaluation**: Handles incoming transactions instantly through a Spring Boot REST API.
2. **Hybrid Detection Approach**:
    - **Rules-Based Engine**: Applies strict, predefined thresholds (e.g., maximum daily transactions, transaction limits) to quickly catch obvious fraud.
    - **Machine Learning**: Predicts the likelihood of fraud for complex cases based on historical patterns (e.g., transaction amount and geographical location).
3. **Microservices Architecture**: Separation of concerns between the transaction processing backend and the machine learning model.

## Working Mechanism
When a client submits a new transaction to the system:
1. **API Controller**: The Java Spring Boot `TransactionController` receives the transaction payload.
2. **Rule Evaluation**: The `FraudDetectionService` evaluates the transaction against strict rules provided by the `RuleEngine`. If a rule is violated, the transaction is immediately rejected with a `403 Forbidden` response.
3. **ML Prediction**: If the transaction passes the rule checks, the service queries the Python Flask ML Service (`/predict` endpoint). 
4. **Final Decision**: The ML service returns a fraud probability. If the probability exceeds the system's threshold, it is flagged as fraudulent. Otherwise, the transaction is approved (`200 OK`).

## Technology Stack
- **Backend Service**: Java 17, Spring Boot 3.1.x, Maven, Lombok
- **Machine Learning Service**: Python, Flask, Joblib (scikit-learn integration ready)
- **Documentation**: Mermaid UML Sequence and Class diagrams.

## Repository Structure
- `/backend`: The main Spring Boot application that orchestrates the detection logic.
- `/ml_service`: A Python Flask microservice serving the ML prediction endpoint.
- `/docs`: Contains UML architecture diagrams outlining the system's sequence and structure.
