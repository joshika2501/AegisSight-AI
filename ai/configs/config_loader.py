"""
Configuration Loader for AegisSight AI
"""

from pathlib import Path
import yaml


class ConfigLoader:
    """Loads values from config.yaml."""

    def __init__(self):
        config_path = Path(__file__).parent / "config.yaml"

        with open(config_path, "r", encoding="utf-8") as f:
            self.config = yaml.safe_load(f)

    def get(self, *keys, default=None):
        value = self.config

        for key in keys:
            if isinstance(value, dict):
                value = value.get(key, default)
            else:
                return default

        return value