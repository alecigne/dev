import argparse


def create_argument_parser():
    parser = argparse.ArgumentParser(description="calculate X to the power of Y")
    parser.add_argument("x", type=int, help="the base")
    parser.add_argument("y", type=int, help="the exponent")
    parser.add_argument("-v", "--verbosity", type=int, choices=[0, 1, 2],
                        help="increase output verbosity")
    return parser


def power(a, b):
    return a ** b


if __name__ == '__main__':
    # Standalone
    arg_parser = create_argument_parser()
    args = arg_parser.parse_args()

    answer = power(args.x, args.y)

    if args.verbosity == 2:
        print("{} to the power {} equals {}".format(args.x, args.y, answer))
    elif args.verbosity == 1:
        print("{}^{} == {}".format(args.x, args.y, answer))
    else:
        print(answer)
