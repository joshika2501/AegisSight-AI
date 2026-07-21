def calculate_risk(
        people,
        intrusion,
        vehicles):

    score = 0

    score += min(people * 4, 40)

    score += min(vehicles * 2, 20)

    if intrusion:
        score += 40

    return min(score, 100)