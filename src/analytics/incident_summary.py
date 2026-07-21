def generate_summary(data):

    summary = []

    summary.append(
        f"{data['people']} people detected."
    )

    summary.append(
        f"{data['vehicles']} vehicles detected."
    )

    if data["intrusion"]:
        summary.append(
            "Restricted zone intrusion detected."
        )

    summary.append(
        f"Risk Level: {data['risk_level']} ({data['risk_score']}%)."
    )

    return " ".join(summary)