;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.8 Fixed-Size Data / Adding Structure / Designing with Structures
;; Distance of objects in a 3-dimensional space to the origin

#lang htdp/bsl

;; * Data definitions

(define-struct 3dposition [x y z])
;; A 3dPosition is a structure:
;; (make-3dposition Number Number Number)
;; Interpretation: a position in a 3D space.
;; Examples:
(define 3dposition1 (make-3dposition 1 2 13))
(define 3dposition2 (make-3dposition -1 0 3))

;; * Functions

;; distance-to-origin: 3dPosition -> Number
;; Compute the distance to origin of a point in a 3D space.
(define (distance-to-origin 3dposition)
  (sqrt (+ (sqr (3dposition-x 3dposition))
           (sqr (3dposition-y 3dposition))
           (sqr (3dposition-z 3dposition)))))

;; * Tests

(check-within
 (inexact->exact (distance-to-origin 3dposition1)) 13.19 0.01)

(check-within
 (inexact->exact (distance-to-origin 3dposition2)) 3.162 0.01)
