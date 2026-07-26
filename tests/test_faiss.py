import time
import numpy as np

from ai.core.models import FeatureRecord
from ai.crossview.faiss_index import FAISSIndex


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

    print("=" * 60)

    print("Indexed Vehicles :", index.size())

    query = np.random.rand(768).astype(np.float32)

    query /= np.linalg.norm(query)

    matches = index.search(query, k=3)

    print("Returned Matches :", len(matches))

    for m in matches:

        print(m.vehicle_id)

    print("=" * 60)


if __name__ == "__main__":
    main()