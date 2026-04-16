import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
import joblib
import os

def train_dummy_model():
    print("Generating simulated historical transaction data...")
    # Amount, LocationRisk (0-1), IsFraud (target)
    data = [
        [100, 0.1, 0],
        [50, 0.0, 0],
        [1500, 0.2, 0],
        [8000, 0.8, 1], # Fraud! High amount and risk
        [5000, 0.9, 1], # Fraud! High risk location
        [6000, 0.3, 0],
        [9000, 0.9, 1],
    ]
    
    df = pd.DataFrame(data, columns=['Amount', 'LocationRisk', 'IsFraud'])
    
    X = df[['Amount', 'LocationRisk']]
    y = df['IsFraud']
    
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
    
    print("Training Random Forest Classifier...")
    clf = RandomForestClassifier(n_estimators=10, random_state=42)
    clf.fit(X_train, y_train)
    
    print(f"Model Accuracy on Test Data: {clf.score(X_test, y_test)}")
    
    # Save the model
    os.makedirs('model', exist_ok=True)
    joblib.dump(clf, 'model/trained_model.pkl')
    print("Model saved to model/trained_model.pkl")

if __name__ == "__main__":
    train_dummy_model()
