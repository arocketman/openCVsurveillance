openCVsurveillance
==================

OpenCV-sorveglianza AKA motion triggered cam 

==================

Software developed with JAVA and OPENCV (originally 2.4 , updated now to 3.2) by Andrea Capuano & Mattia De Prisco for the "Multimedia system" exam.

It's a simple surveillance system based on your webcam. The system will record movement and save it to a "gif" file.
Files won't be created if no movement is detected.

Motion Triggered Cam will attach itself to your webcam and begin its loop:

* Wait for movement
* Record if there's any movement. This is done by performing a background subtraction.
* Capture the webcam image and save it to different ".jpeg" giles
* Put the images in a single .gif

The final result is a series of gifs , indicating the time, which will show the recording at the time the program registered a significant movement.
