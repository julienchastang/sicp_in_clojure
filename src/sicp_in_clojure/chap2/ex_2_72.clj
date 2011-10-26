;; In order to answer this question, we should pick apart encode to see what is going on

;; The append function is O(N)

;; encode-symbol seems to be log2(N), but this will be overwhelmed by the O(N) term.

;; Encode recursively traverses the "tree list" with the O(N) at each point so it seems the result will be O(N^2).