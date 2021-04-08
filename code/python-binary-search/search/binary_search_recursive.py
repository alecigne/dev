def search(array, target, low=0, high=None):
    """Recursive binary search."""
    if high is None:
        high = len(array) - 1
    if low > high:
        return -1

    mid = (low + high) // 2
    val = array[mid]
    if val > target:
        return search(array, target, low, mid - 1)
    elif val < target:
        return search(array, target, mid + 1, high)
    else:
        return mid
