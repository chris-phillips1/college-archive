import sc2
from sc2 import run_game, maps, Race, Difficulty
from sc2.player import Bot, Computer
from sc2.constants import NEXUS, PROBE, PYLON, ASSIMILATOR, GATEWAY, \
 CYBERNETICSCORE, STALKER
import random


class ChrisBot(sc2.BotAI):
    def __init__(self):
        self.ITERATIONS_PER_MINUTE = 165
        self.MAX_WORKERS = 50

    async def on_step(self, iteration):
        self.iteration = iteration
        await self.distribute_workers()
        await self.build_workers()
        await self.build_pylons()
        await self.build_assimilators()
        await self.expand()
        await self.build_military_structures()
        await self.build_stalkers()
        await self.attack_or_defend()

    # A method that will build workers at each nexus, until either the max number of workers has been met
    # or the number of workers is more than 20 per nexus.
    async def build_workers(self):
        if (len(self.units(NEXUS)) * 20) > len(self.units(PROBE)) and len(self.units(PROBE)) < self.MAX_WORKERS:
            for nexus in self.units(NEXUS).ready.noqueue:
                proximal_workers = self.units(PROBE).closer_than(15.0, nexus) # How i check to see how many workers are assigned to this nexus
                if(len(proximal_workers) < 20) and self.can_afford(PROBE):
                        await self.do(nexus.train(PROBE))

    # A method that will build pylons, as they become necessary, around the primary nexus.
    async def build_pylons(self):
        if self.supply_left < 5 and not self.already_pending(PYLON):
            if self.can_afford(PYLON):
                await self.build(PYLON, near=self.units(NEXUS).first)

    # A method that will locate any vespene geyers near every nexus and, if I can afford it, will select a worker
    # and build an assimilator on top of the geyser.
    async def build_assimilators(self):
        for nexus in self.units(NEXUS).ready:
            vaspenes = self.state.vespene_geyser.closer_than(10.0, nexus)
            for vaspene in vaspenes:
                if not self.can_afford(ASSIMILATOR):
                    break
                worker = self.select_build_worker(vaspene.position)
                if worker is None:
                    break
                if not self.units(ASSIMILATOR).closer_than(1.0, vaspene).exists:
                    await self.do(worker.build(ASSIMILATOR, vaspene))

    # A method that will allow my faction to grow to a new harvesting area around every 2 minutes.
    async def expand(self):
        if self.units(NEXUS).amount < ((self.iteration / self.ITERATIONS_PER_MINUTE * 2)) and self.can_afford(NEXUS):
            await self.expand_now()

    # A method that will build the two military structures necessary for the construction of the stalker
    # unit, the gateway and the cybernetics core, with new gateways being created around every 2 minutes.
    async def build_military_structures(self):
        if self.units(PYLON).ready.exists:
            pylon = self.units(PYLON).ready.random

            if self.units(GATEWAY).ready.exists and not self.units(CYBERNETICSCORE):
                if self.can_afford(CYBERNETICSCORE) and not self.already_pending(CYBERNETICSCORE):
                    await self.build(CYBERNETICSCORE, near=pylon)

            if len(self.units(GATEWAY)) < ((self.iteration / self.ITERATIONS_PER_MINUTE)/2):
                if self.can_afford(GATEWAY) and not self.already_pending(GATEWAY):
                    await self.build(GATEWAY, near=pylon)

    # A method that will look at every gateway and, if there's supply life and it can be afforded, will purchase a stalker at every gateway
    async def build_stalkers(self):
        for gw in self.units(GATEWAY).ready.noqueue:
            if self.supply_left > 0:
                if self.can_afford(STALKER):
                    await self.do(gw.train(STALKER))

    # A method that will determine where the units should be attacking, based on our current information
    def find_target(self, state):
        if len(self.known_enemy_units) > 0:
            return random.choice(self.known_enemy_units)
        elif len(self.known_enemy_structures) > 0:
            return random.choice(self.known_enemy_structures)
        else:
            return self.enemy_start_locations[0]

    # The bulk of the winning strategy. This is a method that will go on the offensive once a certain
    # number, 10, of stalkers have been created. If attacked when stalker supply is less than 10 and greater than 3,
    # the stalkers will be tasked with defending the base.
    async def attack_or_defend(self):

        # {UNIT: [n to fight, n to defend]}
        unit_amounts = {STALKER: [10, 3]}

        #defend mode: if there's 5 enemy units within range of the nexus, send units to defend
        if len(self.known_enemy_units.closer_than(75, self.units(NEXUS).first)) > 5:
            for UNIT in unit_amounts:
                if self.units(UNIT).amount > unit_amounts[UNIT][1]:
                    for s in self.units(UNIT).idle:
                        await self.do(s.attack(self.known_enemy_units.closest_to(self.units(NEXUS).first)))
                        
        #attack mode: if there are enough friendly units to mount an attack, head towards the enemy base
        else:
            for UNIT in unit_amounts:
                if self.units(UNIT).amount > unit_amounts[UNIT][0] and self.units(UNIT).amount > unit_amounts[UNIT][1]:
                    for s in self.units(UNIT).idle:
                        await self.do(s.attack(self.find_target(self.state)))


run_game(maps.get("AbyssalReefLE"), [
    Bot(Race.Protoss, ChrisBot()),
    Computer(Race.Terran, Difficulty.Medium)
    ], realtime = False)
