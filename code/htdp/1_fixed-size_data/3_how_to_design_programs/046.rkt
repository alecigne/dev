;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.7 Fixed-Size Data / How to Design Programs / Virtual Pet Worlds
;; Exercise 46

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; Constants

(define CAT (bitmap "files/cat.png"))
(define CAT2 (bitmap "files/cat2.png"))
(define CAT-WIDTH (image-width CAT))
(define CAT-HEIGHT (image-height CAT))
(define WORLD-HEIGHT (+ 20 CAT-HEIGHT))
(define WORLD-WIDTH 400)
(define SCENE (empty-scene WORLD-WIDTH WORLD-HEIGHT))
(define INIT-CAT-X (- (/ CAT-WIDTH 2)))
(define CAT-Y (- (- WORLD-HEIGHT (/ (image-height CAT) 2)) 1))

;; Data definitions

;; A CatPosition is a Number.
;; Interpretation: number of pixels between the left border of the
;; scene and the right edge of the cat.

;; Functions

;; draw-cat: CatPosition -> Image
;; Draw the cat a the required position.
(check-expect (draw-cat 0)
              (place-image CAT INIT-CAT-X CAT-Y SCENE))
(check-expect (draw-cat CAT-WIDTH)
              (place-image CAT 75/2 CAT-Y SCENE))
(define (draw-cat cat-position)
  (place-image (select-cat-from-position cat-position)
               (+ INIT-CAT-X cat-position)
               CAT-Y
               SCENE))

;; select-cat-from-position: CatPosition -> Image
;; Select a cat image based on the position.
(check-expect (select-cat-from-position 1) CAT)
(check-expect (select-cat-from-position 2) CAT2)
(define (select-cat-from-position cat-position)
  (if (odd? cat-position) CAT CAT2))

;; next-position: CatPosition -> CatPosition
;; Get the cat's next position.
(check-expect (next-position 10) 13)
(check-expect (next-position 0) 3)
(check-expect (next-position 472) 0)
(define (next-position cat-position)
  (modulo (+ cat-position 3)
          (+ WORLD-WIDTH CAT-WIDTH)))

;; Main

;; main: CatPosition -> CatPosition
;; Drive the program from an initial cat position.
(define (main initial-cat-position)
  (big-bang initial-cat-position
    [to-draw draw-cat]
    [on-tick next-position]))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
