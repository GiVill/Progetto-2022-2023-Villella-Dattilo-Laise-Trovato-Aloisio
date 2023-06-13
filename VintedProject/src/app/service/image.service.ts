import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor() { }



  static async setProductImageSrc(image: Blob): Promise<string> {
    if (!image) {
      return 'assets/resources/vestito.jpg';
    }

    const cleanedBase64Image = this.base64FromBlob(image);
    const imageBlob = this.base64ToBlob(await cleanedBase64Image);
    return URL.createObjectURL(imageBlob);
  }

  static base64FromBlob(blob: Blob): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      const reader = new FileReader();
      reader.onloadend = () => {
        resolve(reader.result as string);
      };
      reader.onerror = reject;
      reader.readAsDataURL(blob);
    });
  }

  static base64ToBlob(base64Data: string): Blob {
    const byteString = atob(base64Data);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const uint8Array = new Uint8Array(arrayBuffer);

    for (let i = 0; i < byteString.length; i++) {
      uint8Array[i] = byteString.charCodeAt(i);
    }

    const blob = new Blob([arrayBuffer], { type: 'image/jpeg' }); // Assuming the image is in JPEG format

    return blob;
  }

}
