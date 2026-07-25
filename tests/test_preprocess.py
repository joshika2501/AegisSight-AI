import cv2

from ai.embedding.preprocess import ImagePreprocessor


def main():

    image = cv2.imread("sample_data/road.jpg")

    if image is None:
        raise FileNotFoundError("road.jpg not found.")

    preprocessor = ImagePreprocessor()

    tensor = preprocessor.preprocess(image)

    print("=" * 50)
    print("PREPROCESS SUCCESS")
    print("=" * 50)
    print("Tensor Shape :", tensor.shape)
    print("Tensor Type  :", tensor.dtype)
    print("=" * 50)


if __name__ == "__main__":
    main()