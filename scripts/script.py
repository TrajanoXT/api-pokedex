import requests
import time

BASE_URL = "http://localhost:8080"
POKEAPI_URL = "https://pokeapi.co/api/v2"

TIPOS_PT = {
    "normal": "Normal", "fire": "Fogo", "water": "Água",
    "electric": "Elétrico", "grass": "Planta", "ice": "Gelo",
    "fighting": "Lutador", "poison": "Veneno", "ground": "Terra",
    "flying": "Voador", "psychic": "Psíquico", "bug": "Inseto",
    "rock": "Pedra", "ghost": "Fantasma", "dragon": "Dragão",
    "dark": "Sombrio", "steel": "Aço", "fairy": "Fada"
}

def cadastrar_tipos():
    print("Cadastrando tipos...")
    tipos_map = {}

    for nome_en, nome_pt in TIPOS_PT.items():
        response = requests.post(f"{BASE_URL}/tipos", json={"nome": nome_pt})
        if response.status_code == 201:
            tipo = response.json()
            tipos_map[nome_en] = tipo["id"]
            print(f"  ✓ {nome_pt}")
        else:
            print(f"  ✗ Erro ao cadastrar {nome_pt}: {response.text}")

    return tipos_map

def buscar_tipos_cadastrados():
    print("Buscando tipos já cadastrados...")
    response = requests.get(f"{BASE_URL}/tipos")
    tipos_map = {}

    TIPOS_PT_INVERTIDO = {v: k for k, v in TIPOS_PT.items()}

    for tipo in response.json():
        nome_en = TIPOS_PT_INVERTIDO.get(tipo["nome"])
        if nome_en:
            tipos_map[nome_en] = tipo["id"]

    return tipos_map

def buscar_pokemon(numero):
    response = requests.get(f"{POKEAPI_URL}/pokemon/{numero}")
    if response.status_code != 200:
        return None
    return response.json()

def montar_payload(dados, tipos_map):
    stats = {s["stat"]["name"]: s["base_stat"] for s in dados["stats"]}
    tipo_ids = [
        tipos_map[t["type"]["name"]]
        for t in dados["types"]
        if t["type"]["name"] in tipos_map
    ]

    artwork = dados["sprites"]["other"]["official-artwork"]

    return {
        "numeroPokedex": dados["id"],
        "nome": dados["name"].capitalize(),
        "altura": dados["height"] / 10,
        "peso": dados["weight"] / 10,
        "tipoIds": tipo_ids,
        "imagemUrl": artwork.get("front_default"),
        "imagemShinyUrl": artwork.get("front_shiny"),
        "estatisticas": {
            "hp": stats.get("hp", 0),
            "ataque": stats.get("attack", 0),
            "defesa": stats.get("defense", 0),
            "ataqueEspecial": stats.get("special-attack", 0),
            "defesaEspecial": stats.get("special-defense", 0),
            "velocidade": stats.get("speed", 0)
        }
    }

def sincronizar_pokemons(tipos_map, inicio=1, fim=1025):
    print(f"\nSincronizando Pokémon #{inicio} ao #{fim}...")
    sucesso = 0
    erros = []

    for numero in range(inicio, fim + 1):
        dados = buscar_pokemon(numero)

        if not dados:
            print(f"  ✗ #{numero} não encontrado na PokéAPI")
            erros.append(numero)
            continue

        payload = montar_payload(dados, tipos_map)
        response = requests.post(f"{BASE_URL}/pokemons", json=payload)

        if response.status_code == 201:
            print(f"  ✓ #{numero} {payload['nome']}")
            sucesso += 1
        else:
            print(f"  ✗ #{numero} {payload['nome']} — {response.text}")
            erros.append(numero)

    print(f"\nConcluído: {sucesso} salvos, {len(erros)} erros")
    if erros:
        print(f"Erros nos números: {erros}")

if __name__ == "__main__":
    tipos_map = buscar_tipos_cadastrados()

    if not tipos_map:
        tipos_map = cadastrar_tipos()

    sincronizar_pokemons(tipos_map)