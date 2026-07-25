import cv2

from ai.embedding.dinov2_encoder import DINOv2Encoder


def main():

    image = cv2.imread("sample_data/road.jpg")

    if image is None:
        raise FileNotFoundError("sample_data/road.jpg not found.")

    encoder = DINOv2Encoder()

    embedding = encoder.encode(image)

    print("=" * 60)
    print("DINOv2 TEST")
    print("=" * 60)

    print("Embedding Shape :", embedding.shape)
    print("Embedding dtype :", embedding.dtype)

    print("=" * 60)


if __name__ == "__main__":
    main()