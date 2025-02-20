{
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-parts.url = "github:hercules-ci/flake-parts";
    systems.url = "github:nix-systems/default";
  };

  outputs =
    inputs@{ ... }:
    inputs.flake-parts.lib.mkFlake { inherit inputs; } {
      systems = import inputs.systems;

      perSystem =
        { pkgs, ... }:
        let
          jdk = pkgs."jdk21";
        in
        {
          devShells.default = pkgs.mkShellNoCC {
            packages = [
              jdk
              (pkgs.gradle.override { java = jdk; })
            ];
          };
        };
    };
}
