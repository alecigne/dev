def search(arr, target):
    """Binary search."""
    low, high = 0, len(arr) - 1
    while low <= high:
        mid = (high + low) // 2
        val = arr[mid]
        if val == target:
            return mid
        elif val > target:
            high = mid - 1
        else:
            low = mid + 1
    return -1
