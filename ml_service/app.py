import os
import joblib
from flask import Flask, request, jsonify

app = Flask(__name__)

# In a real scenario, we'd load a trained model:
# model = joblib.load('model/trained_model.pkl')

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    if not data:
        return jsonify({"error": "No input data provided"}), 400

    amount = data.get('amount', 0.0)
    location = data.get('location', '')

    # --- Dummy ML Logic ---
    # Here we simulate an ML model predicting fraud.
    # We pretend the model flags transactions over $5000 
    # or from locations like "HighRiskLocation".
    
    probability = 0.01

    if amount > 5000:
        probability += 0.8
    if location.lower() == "highrisklocation":
        probability += 0.6

    is_fraud = probability > 0.7

    return jsonify({
        "fraud": is_fraud,
        "probability": min(probability, 1.0)
    })

if __name__ == '__main__':
    # Start the Flask app
    app.run(host='0.0.0.0', port=5000)
