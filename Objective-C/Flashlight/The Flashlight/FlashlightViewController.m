//
//  FlashlightViewController.m
//  The Flashlight
//
//  Created by Daniel Miedema on 6/26/12.
//  Copyright (c) 2012 NerdyGhost. All rights reserved.
//

#import "FlashlightViewController.h"
#import <AVFoundation/AVFoundation.h>

@interface FlashlightViewController ()

@end

@implementation FlashlightViewController

@synthesize flashlightButton;
@synthesize AVSession;
@synthesize flashlightOn;


#pragma mark - Toggle Flashlight

/* this code works. */

- (void)toggleFlashlight {
    AVCaptureDevice *device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
   
    
    if (device.torchMode == AVCaptureTorchModeOff) {
        // Set up session
        AVCaptureSession *session;
        
        // start session config
        [session beginConfiguration];
        [device lockForConfiguration:nil];
        
        // turn that torch on
        [device setTorchMode:AVCaptureTorchModeOn];
        flashlightOn = YES;
        
        [device unlockForConfiguration];
        [session commitConfiguration];
        
        // start that session
        [session startRunning];
        
        // keep the session around for a bit
        [self setAVSession:session];
    } else {
        // Set up session
        AVCaptureSession *session;

        // start session config
        [session beginConfiguration];
        [device lockForConfiguration:nil];
        
        // turn that torch on
        [device setTorchMode:AVCaptureTorchModeOff];
        flashlightOn = NO;
        
        [device unlockForConfiguration];
        [session commitConfiguration];
        
        // start that session
        [session startRunning];
        
        // keep the session around for a bit
        [self setAVSession:session];
    }

}


- (void) turnTheTorchOnAfterBeingSuspended {
    AVCaptureDevice *device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
    // NSLog(@"turnTheTorchOnAfterBeingSuspended ran");
    if (device.hasTorch) {
        AVCaptureSession *session;
        
        // start session config
        [session beginConfiguration];
        [device lockForConfiguration:nil];
        
        // turn that torch on
        [device setTorchMode:AVCaptureTorchModeOn];
        flashlightOn = YES;
        
        [device unlockForConfiguration];
        [session commitConfiguration];
        
        // start that session
        [session startRunning];
        
        // keep the session around for a bit
        [self setAVSession:session];
    }
}


#pragma mark - Button Pressed
- (IBAction)buttonPressed:(UIButton *)sender {
    if (flashlightOn == NO) {
        flashlightOn = YES;
        [flashlightButton setImage:[UIImage imageNamed:@"onV2-normal.png"]  forState:UIControlStateNormal];
        [flashlightButton setImage:[UIImage imageNamed:@"onV2-highlighted.png"] forState:UIControlStateHighlighted];
    } else {
        flashlightOn = NO;
        [flashlightButton setImage:[UIImage imageNamed:@"offV2-normal.png"]  forState:UIControlStateNormal];
        [flashlightButton setImage:[UIImage imageNamed:@"offV2-highlighted.png"] forState:UIControlStateHighlighted];
    }
    [self toggleFlashlight];
}


#pragma mark - View Code
- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    AVCaptureDevice *device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
    
    if ( [device hasTorch] ) {
        [self toggleFlashlight];
        
        // UIColor *background = [[UIColor alloc] initWithPatternImage:[UIImage imageNamed:@"backgroundV2.png"]];
        // UIColor *background = [[UIColor alloc] initWithPatternImage:[UIImage imageNamed:@"Backgroundwithtexture.png"]];
        CAGradientLayer *bgLayer = [Background greyGradient];
        bgLayer.frame = self.view.bounds;
        [self.view.layer insertSublayer:bgLayer atIndex:0];
        //UIColor *background = [[UIColor alloc] initWithPatternImage:];
        // self.view.backgroundColor = background;
        
        
        
        [flashlightButton setImage:[UIImage imageNamed:@"onV2-normal.png"]  forState:UIControlStateNormal];
        [flashlightButton setImage:[UIImage imageNamed:@"onV2-highlighted.png"] forState:UIControlStateHighlighted];
    }
}

- (void)viewDidUnload
{
    [self setFlashlightButton:nil];
    [super viewDidUnload];
    // Release any retained subviews of the main view.
}


- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{    
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPhone && (interfaceOrientation == UIInterfaceOrientationPortrait) ) {
        return YES;
    } else return NO;
}

@end
