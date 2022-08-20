<div align="center">
<h3>EdgeX-Detector (An Edge Detection App)</h1>
</div>
<br>
<div align="center">
<img width="260px" height = "260px" src="https://user-images.githubusercontent.com/89024718/185718985-13b61ab3-0715-445f-b093-c4f285fb44ea.png" alt="cover" />
</div>
<br>

### About the Application

According to the problem statement provided, there should be a  simple Android app that allows users to upload an image, process the image, and store the
resultant image and original image in a database. Moreover, we can implement users to upload images from URL, work on black&white and color images and also option to take image from camera.

My idea was to make a Kotlin based Application which will use some edge detection library built over openCV, so that it becomes easy for me to do the job done. On the Landing Screen, it contains 4 options, 3 of them are the ways user can provide the image i.e with url, uploading from local file or camera shots.
The last option is the option to view all the saved collections (original image and the processed one). We have used RoomDB to perform the DB operation so that each user can have unique collection on their storage saved locally.
It's very easy to use with minimalistic UI.

### Features 

Edge detection of the images provided through:

- Uploading Image from local storage
- Doing Camera Captures
- Providing URL of the specific image
<br>
- The original and the processed image is then stored to the local RoomDB

### My strategy and Application FLow

On the basis of the challenge provided, I decided to use the following to make my job done in most simplest ways: 

- URI parsing to convert the url into uri
- CameraX for camera feature
- Simple explicit intent for Uploading images from gallery/local media
- RoomDB for Storing purposes
- EdgeDetection Library (this library was not perfectly a OpenCV product but a GPU IMAGE rendering library which supports many filters among which I found EdgeDetection too.)

Application flow is somewhat like this: 

<img width="480px" height = "360px" src="https://user-images.githubusercontent.com/89024718/185720499-da6f9a33-6509-4c2b-8945-a738e0f9a1e3.png" alt="cover" />

### Problems Faced while developing

While making the application, the most challenging part was to get a library for edge detection. Initially, I searched for it, but found nothing. After more searches, I got to know about openCV sdk's for android. I used them but I was stuck on the setup part. 
Then finally I got <a href="https://github.com/cats-oss/android-gpuimage" target="_blank">this</a> 

<br>

### Preview

<div align="center">
<img width="700px" height = 480px" src="https://user-images.githubusercontent.com/89024718/185759441-0044df37-ad5b-434d-ba94-375d76a6ebb0.gif" alt="cover" />
</div>

<br>

<a href="https://drive.google.com/file/d/110kOB1cY6xmyFLRmNO98q3tkMnwuxN__/view?usp=sharing" title="Click here" style="background-color:#FFFFFF;color:#000000;text-decoration:none">Demo Video Link</a>
                                                                                                                                
<a href="https://github.com/Subhadiptech" title="Click here" style="background-color:#FFFFFF;color:#000000;text-decoration:none">App Link</a>
                                                                                                                                
#### Note - Please accept install anyway option for installation as it is not hosted on playstore.
                                   
<div align="center">
###### Copyright-2022, Logo and App Developed By Subhadip Das
</div>                                                                                                                                



