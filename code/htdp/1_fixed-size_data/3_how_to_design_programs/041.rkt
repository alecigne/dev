;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.6 Fixed-Size Data / How to Design Programs / Designing World Programs
;; Exercise 41

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; Physical constants

(define WORLD-WIDTH 400)
(define WORLD-HEIGHT 100)

(define WHEEL-RADIUS 10)
(define WHEEL-DISTANCE (* WHEEL-RADIUS 3))
(define WHEEL-COLOR "black")

(define CAR-LENGTH (* WHEEL-RADIUS 8))
(define CAR-HEIGHT (/ CAR-LENGTH 4))
(define CAR-COLOR "red")

;; Graphical constants

(define WHEEL (circle WHEEL-RADIUS "solid" WHEEL-COLOR))
(define SPACE (rectangle WHEEL-DISTANCE 0 "solid" "white"))
(define FRAME (beside WHEEL SPACE WHEEL))
(define CAR-BODY (rectangle CAR-LENGTH CAR-HEIGHT "solid" CAR-COLOR))

(define CAR
  (underlay/align/offset "middle" "bottom"
                         CAR-BODY
                         0 (/ CAR-HEIGHT 2)
                         FRAME))

(define CAR-Y (- WORLD-HEIGHT (/ (image-height CAR) 2)))

(define TREE
  (overlay/xy (circle 20 "solid" "green")
              16 18
              (rectangle 8 50 "solid" "brown")))

(define BACKGROUND (place-image
                    TREE
                    200 (- WORLD-HEIGHT (/ (image-height TREE) 2))
                    (empty-scene WORLD-WIDTH WORLD-HEIGHT)))

;; Data definitions

;; A CarPosition is a Number.
;; Interpretation: number of pixels between the left border of the scene and the
;; center of the car.

;; Functions

;; draw-car: CarPosition -> Image
;; Draw a car at the required position.
;; Expectations:
(check-expect (draw-car 0) (place-image CAR 0 CAR-Y BACKGROUND))
(check-expect (draw-car 50) (place-image CAR 50 CAR-Y BACKGROUND))
(check-expect (draw-car 200) (place-image CAR 200 CAR-Y BACKGROUND))
(define (draw-car car-position)
  (place-image CAR car-position CAR-Y BACKGROUND))

;; move-car: CarPosition -> CarPosition
;; Move the car by 3 pixels for every clock tick.
(check-expect (move-car 0) 3)
(check-expect (move-car 50) 53)
(define (move-car car-position)
  (+ car-position 3))

;; car-at-limit?: CarPosition -> Boolean
;; Check if the car has reached the limit of the scene.
(check-expect (car-at-limit? 400) #true)
(check-expect (car-at-limit? 200) #false)
(define (car-at-limit? car-position)
  (>= car-position WORLD-WIDTH))

;; Main

;; main: CarPosition -> CarPosition
;; Drive the program from an initial car position.
(define (main initial-car-position)
  (big-bang initial-car-position
    [to-draw draw-car]
    [on-tick move-car]
    [stop-when car-at-limit?]))

(main 0)
