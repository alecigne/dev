;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.11 Fixed-Size Data / Adding Structure / More Virtual Pets
;; Chameleon - ex. 92

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

;; Color is a String. It is one of:
;; - "red"
;; - "blue"
;; - "green"
;; Interpretation: the color of the chameleon.

(define-struct vcham [xpos happiness color])
;; A VCham is a structure:
;; (make-vcham XPos Happiness Color)
;; Interpretation: A chameleon, with its current position, happiness
;; level and color.
;; Examples: see tests

;; * Constants

(define CHAM (bitmap "files/cham2.png"))
(define CHAM-WIDTH (image-width CHAM))
(define CHAM-HEIGHT (image-height CHAM))
(define SCENE-HEIGHT (+ 40 CHAM-HEIGHT))
(define SCENE-WIDTH 500)
(define SCENE (empty-scene SCENE-WIDTH SCENE-HEIGHT))
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
    (draw-cham (vcham-color vcham))
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

;; draw-cham: Color -> Image
;; Draw a chameleon from its color.
(define (draw-cham color)
  (overlay CHAM (rectangle CHAM-WIDTH CHAM-HEIGHT "solid" color)))

;; * Next State

;; next: VCham -> VCham
;; Compute the next state of the chameleon.
(define (next vcham)
  (make-vcham (next-position (vcham-xpos vcham))
              (next-happiness (vcham-happiness vcham))
              (vcham-color vcham)))

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
    [(key=? key-event "r") (change-color vcham "red")]
    [(key=? key-event "g") (change-color vcham "green")]
    [(key=? key-event "b") (change-color vcham "blue")]
    [else vcham]))

;; feed: VCham -> VCham
;; Feed the chameleon.
(define (feed vcham)
  (make-vcham (vcham-xpos vcham)
              (if (> (+ (vcham-happiness vcham) FEEDING-EFFECT) 100)
                  100
                  (+ (vcham-happiness vcham) FEEDING-EFFECT))
              (vcham-color vcham)))

;; change-color: VCham Color -> VCham
;; Change the chameleon color.
(define (change-color vcham color)
  (make-vcham (vcham-xpos vcham)
              (vcham-happiness vcham)
              color))

;; * End of the program

;; zero-happiness?: VCham -> Boolean
;; End the program when the chameleon reaches a given state.
(define (zero-happiness? vcham)
  (zero? (vcham-happiness vcham)))

;; * Tests

;; ** Rendering

;; Unhappy green chameleon

(check-expect
 (render (make-vcham 250 0 "green"))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 0 GAUGE-HEIGHT "solid" "red"))
  10 15 "left" "bottom"
  (place-image
   (overlay CHAM (rectangle CHAM-WIDTH CHAM-HEIGHT "solid" "green"))
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 10 "green"))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 10 GAUGE-HEIGHT "solid" "red"))
  10 15 "left" "bottom"
  (place-image
   (overlay CHAM (rectangle CHAM-WIDTH CHAM-HEIGHT "solid" "green"))
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 32 "green"))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 32 GAUGE-HEIGHT "solid" "red"))
  10 15 "left" "bottom"
  (place-image
   (overlay CHAM (rectangle CHAM-WIDTH CHAM-HEIGHT "solid" "green"))
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

;; Neutral blue chameleon
(check-expect
 (render (make-vcham 250 33 "blue"))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 33 GAUGE-HEIGHT "solid" "gold"))
  10 15 "left" "bottom"
  (place-image
   (overlay CHAM (rectangle CHAM-WIDTH CHAM-HEIGHT "solid" "blue"))
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 50 "blue"))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 50 GAUGE-HEIGHT "solid" "gold"))
  10 15 "left" "bottom"
  (place-image
   (overlay CHAM (rectangle CHAM-WIDTH CHAM-HEIGHT "solid" "blue"))
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 65 "blue"))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 65 GAUGE-HEIGHT "solid" "gold"))
  10 15 "left" "bottom"
  (place-image
   (overlay CHAM (rectangle CHAM-WIDTH CHAM-HEIGHT "solid" "blue"))
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

;; Happy red chameleon
(check-expect
 (render (make-vcham 250 66 "red"))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 66 GAUGE-HEIGHT "solid" "green"))
  10 15 "left" "bottom"
  (place-image
   (overlay CHAM (rectangle CHAM-WIDTH CHAM-HEIGHT "solid" "red"))
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 80 "red"))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 80 GAUGE-HEIGHT "solid" "green"))
  10 15 "left" "bottom"
  (place-image
   (overlay CHAM (rectangle CHAM-WIDTH CHAM-HEIGHT "solid" "red"))
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

(check-expect
 (render (make-vcham 250 100 "red"))
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 100 GAUGE-HEIGHT "solid" "green"))
  10 15 "left" "bottom"
  (place-image
   (overlay CHAM (rectangle CHAM-WIDTH CHAM-HEIGHT "solid" "red"))
   (+ INIT-CHAM-X 250)
   CHAM-Y
   SCENE)))

;; ** Next state

(check-expect
 (next (make-vcham 250 90 "red"))
 (make-vcham 253 89 "red"))

;; 500 (world width) + 150 (cham width) + 30 -> 33
(check-expect
 (next (make-vcham 680 100 "blue"))
 (make-vcham 33 99 "blue"))

(check-expect
 (next (make-vcham 0 0 "green"))
 (make-vcham 3 0 "green"))

;; ** Input

(check-expect
 (input (make-vcham 250 90 "red") "down")
 (make-vcham 250 100 "red"))

(check-expect
 (input (make-vcham 250 90 "green") "r")
 (make-vcham 250 90 "red"))

(check-expect
 (input (make-vcham 250 90 "green") "b")
 (make-vcham 250 90 "blue"))

(check-expect
 (input (make-vcham 250 90 "red") "g")
 (make-vcham 250 90 "green"))

(check-expect
 (input (make-vcham 250 90 "green") "w")
 (make-vcham 250 90 "green"))

(check-expect
 (input (make-vcham 250 100 "green") "down")
 (make-vcham 250 100 "green"))

;; ** End

(check-expect
 (zero-happiness? (make-vcham 250 10 "blue"))
 #false)

(check-expect
 (zero-happiness? (make-vcham 250 0 "blue"))
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

(main (make-vcham 0 100 "red"))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
