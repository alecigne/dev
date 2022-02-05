;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.1.4 Fixed-Size Data / Arithmetic / The Arithmetic of Images
;; Exercise 6

#lang racket                            ; with htdp/bsl, images don't
                                        ; appear in Emacs

(require 2htdp/image)

(define cat (bitmap "files/cat.png"))

(* (image-width cat)
   (image-height cat))
