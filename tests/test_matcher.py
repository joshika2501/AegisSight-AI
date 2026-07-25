import time
import numpy as np

from ai.core.models import FeatureRecord
from ai.crossview.faiss_index import FAISSIndex
from ai.crossview.matcher import CrossViewMatcher


def main():

    index = FAISSIndex()

    for i in range(10):

        embedding = np.random.rand(768).astype(np.float32)

        embedding /= np.linalg.norm(embedding)

        record = FeatureRecord(

            vehicle_id=f"CAR_{i}",

            camera_id="CCTV_01",

            timestamp=time.time(),

            embedding=embedding,

        )

        index.add(record)

    query = np.random.rand(768).astype(np.float32)

    query /= np.linalg.norm(query)

    matches = index.search(query, k=5)

    matcher = CrossViewMatcher()

    result = matcher.match(matches)

    print("=" * 60)

    print("MATCH RESULT")

    print("=" * 60)

    print("Status :", result["status"])

    print("Score  :", round(result["score"], 4))

    if result["best_match"]:

        print(
            "Vehicle:",
            result["best_match"].vehicle_id
        )

    print("=" * 60)


if __name__ == "__main__":
    main()