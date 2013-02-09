//
//  FlashlightViewController.h
//  The Flashlight
//
//  Created by Daniel Miedema on 6/26/12.
//  Copyright (c) 2012 NerdyGhost. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>
#import "Background.h"

@interface FlashlightViewController : UIViewController

@property (weak, nonatomic) IBOutlet UIButton *flashlightButton;
@property (weak, nonatomic) AVCaptureSession *AVSession;
@property (nonatomic) BOOL flashlightOn;

-(void) turnTheTorchOnAfterBeingSuspended;

@end
