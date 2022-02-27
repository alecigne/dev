;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.11 Fixed-Size Data / Adding Structure / More Virtual Pets
;; Happy Cat - ex. 88-90

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Data definitions

(define-struct vcat [position happiness])
;; A VCat is a structure: (make-vcat XPos Happiness)
;; Interpretation: (make-vcat xpos happiness) is a virtual cat, with
;; its x position and happiness.
;; Exemples:
(define happy-vcat (make-vcat 250 90))
(define alternative-happy-vcat (make-vcat 249 90))
(define neutral-vcat (make-vcat 250 50))
(define unhappy-vcat (make-vcat 250 10))

;; An XPos is a Number.
;; Interpretation: the number of pixels between the left-side of the
;; scene and the right side of the cat.

;; Happiness is a Number.
;; Interpretation: the level of happiness, from 0 to 100.
;; - "happy": (<= 66 happiness 100)
;; - "neutral": (<= 33 happiness 65)
;; - "unhappy": (<= 0 happiness 32)

;; * Constants

(define CAT1 (bitmap "files/cat.png"))
(define CAT2 (bitmap "files/cat2.png"))
(define CAT-WIDTH (image-width CAT1))
(define CAT-HEIGHT (image-height CAT1))
(define SCENE-HEIGHT (+ 40 CAT-HEIGHT))
(define SCENE-WIDTH 500)
(define SCENE (empty-scene SCENE-WIDTH SCENE-HEIGHT))
(define INIT-CAT-X (- (/ CAT-WIDTH 2)))
(define CAT-Y (- (- SCENE-HEIGHT (/ (image-height CAT1) 2)) 1))
(define GAUGE-HEIGHT (/ SCENE-HEIGHT 20))
(define GAUGE-SPEED 1) ; pixels per clock tick
(define FRAME (rectangle 100 GAUGE-HEIGHT "outline" "black"))
(define PET 5)
(define FEED 10)

;; * Functions

;; ** Rendering

;; render: VCat -> Image
;; Render the virtual cat at the given position and with its happiness
;; gauge.
(define (render vcat)
  (place-image/align
   (overlay/align
    "left" "middle" FRAME
    (draw-gauge (vcat-happiness vcat)))
   10 15 "left" "bottom"
   (place-image (select-cat (vcat-position vcat))
                (+ INIT-CAT-X (vcat-position vcat))
                CAT-Y
                SCENE)))

;; draw-gauge: Happiness -> Image
;; Draw the happiness gauge from a Happiness level.
(define (draw-gauge happiness)
  (rectangle happiness GAUGE-HEIGHT "solid"
             (cond
               [(<= 66 happiness 100) "green"]
               [(<= 33 happiness 65) "gold"]
               [(<= happiness 32) "red"])))

;; select-cat: XPos -> Image
;; Select a cat image depending on its position.
(define (select-cat xpos)
  (if (odd? xpos) CAT1 CAT2))

;; ** Next state

;; next: VCat -> VCat
;; Compute the next state of the virtual cat.
(define (next vcat)
  (make-vcat (next-position (vcat-position vcat))
             (next-happiness (vcat-happiness vcat))))

;; next-position: XPos -> XPos
;; Compute the next position.
(define (next-position position)
  (modulo (+ position 3) (+ CAT-WIDTH SCENE-WIDTH)))

;; next-happiness: Happiness -> Happiness
;; Compute the next happiness level.
(define (next-happiness happiness)
  (if (<= happiness 0) 0 (sub1 happiness)))

;; ** Attending to the cat

;; input: VCat KeyEvent -> VCat
;; Attend to the cat by petting it or feeding it, depending on the key
;; that was pressed.
(define (input vcat key-event)
  (cond
    [(key=? key-event "up") (attend vcat FEED)]
    [(key=? key-event "down") (attend vcat PET)]
    [else vcat]))

;; attend: VCat Number -> VCat
;; Attend to the virtual cat.
(define (attend vcat effect)
  (make-vcat (vcat-position vcat)
             (if (> (+ (vcat-happiness vcat) effect) 100)
                 100
                 (+ (vcat-happiness vcat) effect))))

;; ** Ending the program

;; zero-happiness?: VCat -> Boolean
;; Check if the virtual cat's happiness level has fallen to zero.
(define (zero-happiness? vcat)
  (zero? (vcat-happiness vcat)))

;; * Tests

;; ** Rendering

(check-expect
 (render happy-vcat)
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 90 GAUGE-HEIGHT "solid" "green"))
  10 15 "left" "bottom"
  (place-image CAT2
               (+ INIT-CAT-X 250)
               CAT-Y
               SCENE)))

(check-expect
 (render alternative-happy-vcat)
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 90 GAUGE-HEIGHT "solid" "green"))
  10 15 "left" "bottom"
  (place-image CAT1
               (+ INIT-CAT-X 249)
               CAT-Y
               SCENE)))

(check-expect
 (render neutral-vcat)
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 50 GAUGE-HEIGHT "solid" "gold"))
  10 15 "left" "bottom"
  (place-image CAT2
               (+ INIT-CAT-X 250)
               CAT-Y
               SCENE)))

(check-expect
 (render unhappy-vcat)
 (place-image/align
  (overlay/align
   "left" "middle" FRAME
   (rectangle 10 GAUGE-HEIGHT "solid" "red"))
  10 15 "left" "bottom"
  (place-image CAT2
               (+ INIT-CAT-X 250)
               CAT-Y
               SCENE)))

(check-expect
 (draw-gauge 90)
 (rectangle 90 GAUGE-HEIGHT "solid" "green"))

(check-expect
 (draw-gauge 66)
 (rectangle 66 GAUGE-HEIGHT "solid" "green"))

(check-expect
 (draw-gauge 50)
 (rectangle 50 GAUGE-HEIGHT "solid" "gold"))

(check-expect
 (draw-gauge 33)
 (rectangle 33 GAUGE-HEIGHT "solid" "gold"))

(check-expect
 (draw-gauge 10)
 (rectangle 10 GAUGE-HEIGHT "solid" "red"))

(check-expect
 (draw-gauge 0)
 (rectangle 0 GAUGE-HEIGHT "solid" "red"))

(check-expect (select-cat 249) CAT1)
(check-expect (select-cat 250) CAT2)

;; ** Next state

(check-expect (next happy-vcat) (make-vcat 253 89))
(check-expect (next (make-vcat 1200 100)) (make-vcat 53 99))
(check-expect (next (make-vcat 0 0)) (make-vcat 3 0))

(check-expect (next-position 1200) 53)
(check-expect (next-happiness 100) 99)
(check-expect (next-happiness 0) 0)

;; ** Attending to the cat

(check-expect
 (input happy-vcat "up")
 (make-vcat 250 100))

(check-expect
 (input happy-vcat "down")
 (make-vcat 250 95))

(check-expect
 (input neutral-vcat "up")
 (make-vcat 250 60))

(check-expect
 (input neutral-vcat "down")
 (make-vcat 250 55))

(check-expect
 (input (make-vcat 250 98) "up")
 (make-vcat 250 100))

(check-expect
 (input neutral-vcat "a")
 (make-vcat 250 50))

;; ** Ending the program

(check-expect
 (zero-happiness? (make-vcat 250 0))
 #true)

(check-expect
 (zero-happiness? (make-vcat 250 1))
 #false)

;; * Main

;; main: Void -> VCat
;; Drive the cat program from an initial state.
(define (main vcat)
  (big-bang vcat
    [to-draw render]
    [on-tick next]
    [on-key input]
    [stop-when zero-happiness?]))

(main (make-vcat 0 100))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
