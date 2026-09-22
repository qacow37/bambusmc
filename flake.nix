{
    description = "Flake providing devShells for the development on the plugin";

    inputs = {
        nixpkgs.url = "github:nixos/nixpkgs/nixpkgs-unstable";
        flake-utils.url = "github:numtide/flake-utils";
    };
    outputs = {nixpkgs, flake-utils, ...}:
        flake-utils.lib.eachDefaultSystem (system:
            let
                pkgs = nixpkgs.legacyPackages.${system};
            in
            {
                devShells = {
                    default = pkgs.mkShellNoCC {
                        packages = with pkgs; [
                            jdt-language-server
                            zulu8
                            maven
                        ];
                    };
                };
            }
        );
}
