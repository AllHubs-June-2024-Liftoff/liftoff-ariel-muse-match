import React from "react";


const getImage = async(imageId) => {

try {
   const response = await fetch(`https://www.artic.edu/iiif/2/${imageId}/full/843,/0/default.jpg`)
    if (!response.ok) {
        throw new Error("YO NO IMAGES");
    }
    const imageData = response.json();
    return imageData;
} catch(e) {
   console.error("OO BABY NO IMAGES FO U");
   throw error;
}
}



export default getImage;