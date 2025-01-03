import React from "react";

const getImage = async(imageId) => {

try {
   const response = await fetch(`https://www.artic.edu/iiif/2/${imageId}/full/843,/0/default.jpg`)
    if (!response.ok) {
        throw new Error("No image for the artwork ID");
    }
    //This returns the image in the body
    //as binary data, must construct URL for source
    const imageData = await response.blob();
    //Here's where the URL is constructed into an object, now ImageSrc
    const imageSrc = URL.createObjectURL(imageData);
    //Creates URL to access object
    return imageSrc; //<--returns url
} catch(e) {
   console.error("No images available");
   throw e;
}
}

export default getImage;