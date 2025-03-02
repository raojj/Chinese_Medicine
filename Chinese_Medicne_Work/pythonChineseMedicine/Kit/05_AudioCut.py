from pydub import AudioSegment
import os


def split_audio(input_file, n):
    # Load the audio file
    audio = AudioSegment.from_file(input_file, format="m4a")

    # Calculate duration of each segment
    segment_duration = len(audio) / n

    # Create directory to store output files
    output_dir = os.path.dirname(input_file)
    output_base_name = os.path.splitext(os.path.basename(input_file))[0]

    # Split the audio into segments
    for i in range(n):
        start_time = i * segment_duration
        end_time = (i + 1) * segment_duration if i < n - 1 else len(audio)
        segment = audio[start_time:end_time]

        # Output file path
        output_file = os.path.join(output_dir, f"{output_base_name}_{i + 1}.mp3")

        # Export segment as mp3
        segment.export(output_file, format="mp3")
        print(f"已切割文件 {output_file}")


if __name__ == "__main__":
    input_file = r"F:\工作\Chinese_Medicine\蔡老师\2024-方老师校对-ING\20241029\7.m4a"  # Path to your input M4A file
    n = 1  # Number of segments to split the audio into
    split_audio(input_file, n)
