from ultralytics import YOLO
model = YOLO("yolo11m.pt")
model.train(data="dataset.yaml", imgsz=640, batch_size=8, epochs=100,workers=1,device=0)
