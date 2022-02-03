;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; Prologue: How to Program

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; Constants

(define (make-ufo radius color)
  (overlay (circle radius "solid" color)
           (rectangle (* radius 4) (/ radius 2)
                      "solid" color)))

(define UFO (make-ufo 30 "black"))
(define ROCKET (bitmap "files/rocket.png"))
(define AIRCRAFT ROCKET)
(define AIRCRAFT-INITIAL-ALTITUDE 300)    ; this is relative to the
                                          ; bottom of the aircraft
(define AIRCRAFT-SPEED 3)
(define SCENE-HEIGHT (+ AIRCRAFT-INITIAL-ALTITUDE
                        (image-height AIRCRAFT)
                        10))
(define SCENE-WIDTH (+ (image-width AIRCRAFT) 100))
(define SCENE-X-FOCUS (/ SCENE-WIDTH 2))
(define SCENE (empty-scene SCENE-WIDTH SCENE-HEIGHT))

;; Functions

;; Display the aircraft for a given altitude.
(define (place-aircraft altitude)
  (place-image AIRCRAFT
               SCENE-X-FOCUS
               (aircraft-ypos altitude)
               SCENE))

;; Get aircraft's Y position on the scene for a given altitude.
(define (aircraft-ypos altitude)
  (- SCENE-HEIGHT
     altitude
     (/ (image-height AIRCRAFT) 2)))

;; Compute altitude after a given elapsed time.
(define (altitude t)
  (- AIRCRAFT-INITIAL-ALTITUDE
     (distance AIRCRAFT-SPEED t)))

;; Compute distance from velocity and time.
(define (distance v t)
  (* v t))

;; Control the aircraft's altitude over time.
(define (aircraft-control t)
  (cond                                 ; `if' not introduced yet
    [(> (altitude t) 0)
     (place-aircraft (altitude t))]
    [(<= (altitude t) 0)
     (place-aircraft 0)]))              ; `else' not introduced in cond

;; Main

(animate aircraft-control)
