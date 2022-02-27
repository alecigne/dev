;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.11 Fixed-Size Data / Adding Structure / More Virtual Pets
;; Chameleon - ex. 93

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Data definitions

;; An XPos is a Number.
;; Interpretation: the number of pixels between the left-side of the
;; scene and the right side of the chameleon.

;; Happiness is a Number.
;; Interpretation: the level of happiness, from 0 to 100.
;; - "happy": (<= 66 happiness 100)
;; - "neutral": (<= 33 happiness 65)
;; - "unhappy": (<= 0 happiness 32)

(define-struct vcham [xpos happiness])
;; A VCham is a structure:
;; (make-vcham XPos Happiness)
;; Interpretation: A chameleon, with its current position and
;; happiness.
;; Examples: see tests

;; * Constants

(define CHAM (bitmap "files/cham3.png"))
(define CHAM-WIDTH (image-width CHAM))
(define CHAM-HEIGHT (image-height CHAM))
(define SCENE-HEIGHT (+ 40 CHAM-HEIGHT))
(define SCENE-WIDTH 500)
(define SCENE
  (beside
   (empty-scene (/ SCENE-WIDTH 3) SCENE-HEIGHT "darkolivegreen")
   (empty-scene (/ SCENE-WIDTH 3) SCENE-HEIGHT "white")
   (empty-scene (/ SCENE-WIDTH 3) SCENE-HEIGHT "red")))
(define GAUGE-HEIGHT (/ SCENE-HEIGHT 20))
(define FRAME (rectangle 100 GAUGE-HEIGHT "outline" "black"))
(define INIT-CHAM-X (- (/ CHAM-WIDTH 2)))
(define CHAM-Y (- (- SCENE-HEIGHT (/ (image-height CHAM) 2)) 1))
(define FEEDING-EFFECT 10)

;; * Functions

;; ** Rendering

;; render: VCham -> Image
;; Render the scene from the current state of the chameleon.
(define (render vcham)
  (place-image/align
   (draw-gauge (vcham-happiness vcham))
   10 15 "left" "bottom"
   (place-image
    CHAM
    (+ INIT-CHAM-X (vcham-xpos vcham))
    CHAM-Y
    SCENE)))

;; draw-gauge: Happiness -> Image
;; Draw a gauge from a happiness level.
(define (draw-gauge happiness)
  (overlay/align
   "left" "middle" FRAME
   (rectangle happiness GAUGE-HEIGHT "solid"
              (cond
                [(<= 66 happiness 100) "green"]
                [(<= 33 happiness 65) "gold"]
                [(<= happiness 32) "red"]))))

;; * Next State

;; next: VCham -> VCham
;; Compute the next state of the chameleon.
(define (next vcham)
  (make-vcham (next-position (vcham-xpos vcham))
              (next-happiness (vcham-happiness vcham))))

;; next-position: XPos -> XPos
(define (next-position xpos)
  (modulo (+ xpos 3) (+ SCENE-WIDTH CHAM-WIDTH)))

;; next-happiness: Happiness -> Happiness
(define (next-happiness happiness)
  (if (<= happiness 0) 0 (sub1 happiness)))

;; * Input

;; input: VCham KeyEvent -> VCham
;; Act on the chameleon according to keyboard inputs.
(define (input vcham key-event)
  (cond
    [(key=? key-event "down") (feed vcham)]
    [else vcham]))

;; feed: VCham -> VCham
;; Feed the chameleon.
(define (feed vcham)
  (make-vcham (vcham-xpos vcham)
              (if (> (+ (vcham-happiness vcham) FEEDING-EFFECT) 100)
                  100
                  (+ (vcham-happiness vcham) FEEDING-EFFECT))))

;; * End of the program

;; zero-happiness?: VCham -> Boolean
;; End the program when the chameleon reaches a given state.
(define (zero-happiness? vcham)
  (zero? (vcham-happiness vcham)))

;; * Tests

;; ** Rendering

;; Unhappy green chameleon

(check-expect
 (render (make-vcham 250 0))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 0 GAUGE-HEIGHT "solid" "red"))
  10 15 "left" "bottom"
  (place-image
   CHAM
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 10))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 10 GAUGE-HEIGHT "solid" "red"))
  10 15 "left" "bottom"
  (place-image
   CHAM
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 32))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 32 GAUGE-HEIGHT "solid" "red"))
  10 15 "left" "bottom"
  (place-image
   CHAM
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

;; Neutral blue chameleon
(check-expect
 (render (make-vcham 250 33))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 33 GAUGE-HEIGHT "solid" "gold"))
  10 15 "left" "bottom"
  (place-image
   CHAM
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 50))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 50 GAUGE-HEIGHT "solid" "gold"))
  10 15 "left" "bottom"
  (place-image
   CHAM
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 65))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 65 GAUGE-HEIGHT "solid" "gold"))
  10 15 "left" "bottom"
  (place-image
   CHAM
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

;; Happy red chameleon
(check-expect
 (render (make-vcham 250 66))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 66 GAUGE-HEIGHT "solid" "green"))
  10 15 "left" "bottom"
  (place-image
   CHAM
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 80))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 80 GAUGE-HEIGHT "solid" "green"))
  10 15 "left" "bottom"
  (place-image
   CHAM
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 100))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 100 GAUGE-HEIGHT "solid" "green"))
  10 15 "left" "bottom"
  (place-image
   CHAM
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

;; ** Next state

(check-expect
 (next (make-vcham 250 90))
 (make-vcham 253 89))

;; 500 (world width) + 150 (cham width) + 30 -> 33
(check-expect
 (next (make-vcham 680 100))
 (make-vcham 33 99))

(check-expect
 (next (make-vcham 0 0))
 (make-vcham 3 0))

;; ** Input

(check-expect
 (input (make-vcham 250 90) "down")
 (make-vcham 250 100))

(check-expect
 (input (make-vcham 250 90) "r")
 (make-vcham 250 90))

(check-expect
 (input (make-vcham 250 90) "b")
 (make-vcham 250 90))

(check-expect
 (input (make-vcham 250 90) "g")
 (make-vcham 250 90))

(check-expect
 (input (make-vcham 250 90) "w")
 (make-vcham 250 90))

(check-expect
 (input (make-vcham 250 100) "down")
 (make-vcham 250 100))

;; ** End

(check-expect
 (zero-happiness? (make-vcham 250 10))
 #false)

(check-expect
 (zero-happiness? (make-vcham 250 0))
 #true)

;; * Main

;; main: VCham -> VCham
;; Drive the chameleon program.
(define (main vcham)
  (big-bang vcham
    [to-draw render]
    [on-tick next]
    [on-key input]
    [stop-when zero-happiness?]))

(main (make-vcham 0 100))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
